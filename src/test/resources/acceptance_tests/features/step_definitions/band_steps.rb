Given(/^that the bands' database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
end

When(/^I add a band with name "([^"]*)" and genre "([^"]*)"$/) do |name, genre|
  begin
   response = RestClient.post 'http://localhost:4567/bands/', { :name => name, :genre => genre }, :content_type => 'text/plain'
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(409)
  end
end

Then(/^the bands' database should have (\d+) entries$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)
end

And(/^the entry should have name "([^"]*)" and genre "([^"]*)"$/) do |name, genre|
    resultingName = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select name from bandDB;\" -t`

    #ASK TO THE PROJECT MANAGER!!!!!!!
    resultingName = resultingName.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    name = name.gsub(/[^[:print:]]|\s/,'')
    expect(resultingName).to eq(name)

    resultingGenre = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select genre from bandDB;\" -t`
    resultingGenre = resultingGenre.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingGenre).to eq(genre)
end

Given(/^that the bands' database have (\d+) entries$/) do |cant|
  1..cant.to_i.times do |n|
    response = RestClient.post 'http://localhost:4567/bands/', { :name => "name#{n}", :genre => "genre#{1}" }, :content_type => 'text/plain'
  end

  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(cant.to_s).to eq(result)
end

And(/^the band with name "([^"]*)" and genre "([^"]*)" is not in bands' datebase$/) do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(204)
  end
end

And(/^the bands' database have a band with name "([^"]*)" and genre "([^"]*)"$/) do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

Then(/^the band with name "([^"]*)" and genre "([^"]*)" should be on bands' database$/)do |name, genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

When(/^I remove a band with name "([^"]*)" and genre "([^"]*)"$/)do |name,genre|
  begin
    response = RestClient.delete "http://localhost:4567/bands/:#{name}"
  rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

And(/^the database shouldn't have a band with  name "([^"]*)" and genre "([^"]*)"$/)do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(204)
  end
end

When(/^I update the band with name "([^"]*)" and genre "([^"]*)" to name "([^"]*)" and genre "([^"]*)"$/)do |oldName,oldGenre,newName,newGenre|
  begin
    response = RestClient.put "http://localhost:4567/bands/", { :oldName => oldName, :oldGenre => oldGenre, :newName => newName, :newGenre => newGenre}, :content_type => 'text/plain'
    rescue RestClient::Conflict => e
    expect(e.response).to eq(202)
  end
end

Then(/^the band with name "([^"]*)" and genre "([^"]*)" shouldn't be on bands' database$/)do |name, genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/", { :name => name, :genre => genre}, :content_type => 'text/plain'
    rescue RestClient::Conflict => e
    expect(e.response).to eq(304)
  end
end

# When(/^I update a band with name "([^"]*)" and genre "([^"]*)"$/) do |arg1, arg2|
#   pending
# end

Given(/^the bands' database havn't a band with name "([^"]*)" and genre "([^"]*)"$/) do |arg1, arg2|
  begin
    response = RestClient.get "http://localhost:4567/bands/", { :name => name, :genre => genre}, :content_type => 'text/plain'
    rescue RestClient::Conflict => e
    expect(e.response).to eq(304)
  end
end

# Find Band feature

# Find Band by name
When(/^I search a band with name "([^"]*)", the application must return (\d+) results$/) do |name, entries|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

When(/^I search a band with name "([^"]*)", the band exist in the database, then the application must return (\d+) results$/) do |name, entries|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

When(/^I search a band with name "([^"]*)", the band doesn't exist in the database, then the application must return (\d+) results$/) do |name, entries|
    response = RestClient.post 'http://localhost:4567/bands/', { :name => "ataque77", :genre => "rock" }, :content_type => 'text/plain'
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

# Find Band by genre
When(/^I search a band with genre "([^"]*)", the application must return (\d+) results$/) do |genre, entries|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

When(/^I search a band with genre "([^"]*)", the band exist in the database, then the application must return (\d+) results$/) do |genre, entries|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

When(/^I search a band with genre "([^"]*)", the band doesn't exist in the database, then the application must return (\d+) results$/) do |genre, entries|
  response = RestClient.post 'http://localhost:4567/bands/', { :name => "ataque77", :genre => "rock" }, :content_type => 'text/plain'
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

# Find Band by name and genre
When(/^I search a band with name "([^"]*)", and genre "([^"]*)", the application must return (\d+) results$/) do |name, genre, entries|
  2..entries.to_i.times do |n|
    response = RestClient.post 'http://localhost:4567/bands/', { :name => "name#{n}", :genre => "genre#{1}" }, :content_type => 'text/plain'
  end

  response = RestClient.post 'http://localhost:4567/bands/', { :name => name, :genre => genre }, :content_type => 'text/plain'

  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}' AND genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

When(/^I search a band with name "([^"]*)", and genre "([^"]*)", the band exist in the database, the application must return (\d+) results$/) do |name, genre, entries|
  2..entries.to_i.times do |n|
    response = RestClient.post 'http://localhost:4567/bands/', { :name => "name#{n}", :genre => "genre#{1}" }, :content_type => 'text/plain'
  end

  response = RestClient.post 'http://localhost:4567/bands/', { :name => name, :genre => genre }, :content_type => 'text/plain'

  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}' AND genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

When(/^I search a band with name "([^"]*)", and genre "([^"]*)", the band doesn't exist in the database, the application must return (\d+) results$/) do |name, genre, entries|
  1..entries.to_i.times do |n|
    response = RestClient.post 'http://localhost:4567/bands/', { :name => "name#{n}", :genre => "genre#{1}" }, :content_type => 'text/plain'
  end

  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}' AND genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)
end

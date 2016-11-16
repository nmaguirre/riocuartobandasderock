#----------------------------------------------------------------------------------------#
#
#  The file has the following order :
#     First   "Given" steps
#     Second  "When"  steps
#     Third   "Then"  steps
#     Fourth  "And"   steps
#
#----------------------------------------------------------------------------------------#


#----------------------------------------------------------------------------------------#
# => Given Steps
#----------------------------------------------------------------------------------------#

Given(/^that the bands' database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
end

Given(/^that the bands' database have (\d+) entries$/) do |cant|
  1..cant.to_i.times do |n|
    response = RestClient.post "http://localhost:4567/bands/", { :name => "Band#{n}", :genre => "Nu-Metal" }, :content_type => 'text/plain'
  end
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(cant.to_s).to eq(result)
end

Given(/^the bands' database havn't a band with name "([^"]*)" and genre "([^"]*)"$/) do |arg1, arg2|
  begin
    response = RestClient.get "http://localhost:4567/bands/", { :name => name, :genre => genre}, :content_type => 'text/plain'
    rescue RestClient::Conflict => e
    expect(e.response).to eq(304)
  end
end

#----------------------------------------------------------------------------------------#
# => When Steps
#----------------------------------------------------------------------------------------#

When(/^I add a band with name "([^"]*)" and genre "([^"]*)"$/) do |name, genre|
  begin
   response = RestClient.post 'http://localhost:4567/bands/', { :name => name, :genre => genre }, :content_type => 'text/plain'
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(409)
  end
end

When(/^I remove a band with name "([^"]*)" and genre "([^"]*)"$/)do |name,genre|
  begin
    response = RestClient.delete "http://localhost:4567/bands/:#{name}"
  rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

When(/^I update the band with name "([^"]*)" and genre "([^"]*)" to name "([^"]*)" and genre "([^"]*)"$/)do |oldName,oldGenre,newName,newGenre|
  begin
    response = RestClient.put "http://localhost:4567/bands/", { :oldName => oldName, :oldGenre => oldGenre, :newName => newName, :newGenre => newGenre}, :content_type => 'text/plain'
    rescue RestClient::Conflict => e
    expect(e.response).to eq(202)
  end
end

When(/^I search a band with name "([^"]*)" and the band exist in the database$/) do |name|
  begin
   response = RestClient.get "http://localhost:4567/bands/findbyname/:#{name}"
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(201)
  end
end

When(/^I search a band with name "([^"]*)" and the band doesn't exist in the database$/) do |name|
  begin
   response = RestClient.get "http://localhost:4567/bands/findbyname/:#{name}"
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(204)
  end
end

When(/^I search a band with genre "([^"]*)" and the band exist in the database$/) do |genre|
  begin
   response = RestClient.get "http://localhost:4567/bands/findbygenre/:#{genre}"
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(201)
  end
end

When(/^I search a band with genre "([^"]*)" and the band doesn't exist in the database$/) do |genre|
  begin
   response = RestClient.get "http://localhost:4567/bands/findbygenre/:#{genre}"
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(204)
  end
end

When(/^I search a band with name "([^"]*)" and genre "([^"]*)" and the band exist in the database$/) do |name, genre|
  begin
   response = RestClient.get 'http://localhost:4567/bands/find/', { :name => name, :genre => genre }, :content_type => 'text/plain'
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(201)
  end
end

When(/^I search a band with name "([^"]*)" and genre "([^"]*)" and the band doesn't exist in the database$/) do |name, genre|
  begin
   response = RestClient.get "http://localhost:4567/bands/find/", { :name => name, :genre => genre }, :content_type => 'text/plain'
   rescue RestClient::Conflict => e
   expect(e.response.code).to eq(204)
  end
end

#----------------------------------------------------------------------------------------#
# => Then Steps
#----------------------------------------------------------------------------------------#

Then(/^the bands' database should have (\d+) entries$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)
end

Then(/^the band with name "([^"]*)" and genre "([^"]*)" should be on bands' database$/)do |name, genre|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}' AND genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq("1")
end

Then(/^the band with name "([^"]*)" and genre "([^"]*)" shouldn't be on bands' database$/)do |name, genre|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}' AND genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq("0")
end

Then(/^the band with name "([^"]*)" should be on bands' database$/)do |name|
  begin
    response = RestClient.get "http://localhost:4567/bands/findbyname/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

Then(/^the band with name "([^"]*)" shouldn't be on bands' database$/)do |name|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where name = '#{name}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq("0")
end

Then(/^the band with genre "([^"]*)" should be on bands' database$/)do |genre|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to be >=("1")
end

Then(/^the band with genre "([^"]*)" shouldn't be on bands' database$/)do |genre|
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB where genre = '#{genre}';\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq("0")
end

#----------------------------------------------------------------------------------------#
# => And Steps
#----------------------------------------------------------------------------------------#

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

And(/^the band with name "([^"]*)" and genre "([^"]*)" is not in bands' datebase$/) do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/findbyname/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(204)
  end
end

And(/^the bands' database have a band with name "([^"]*)" and genre "([^"]*)"$/) do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/findbyname/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

And(/^the database shouldn't have a band with  name "([^"]*)" and genre "([^"]*)"$/)do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/bands/findbyname/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(204)
  end
end

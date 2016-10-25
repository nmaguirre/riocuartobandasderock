#encoding: utf-8

require 'rest-client'
require 'json'
require "rspec"
include RSpec::Matchers

HOST = "localhost"
PORT = "5432"



def execute_sql(sql_code)
        done = system "sh db_execute.sh \"#{sql_code}\""
            raise Exception.new("Issue executing sql code: #{sql_code}") unless done
end

Given(/^that the application has been started$/) do
      # Application is started by the setUp routines
      # Nothing to do here...
end

Given(/^I have successfully logged in as admin$/) do
    # Nothing to do here...
end

Given(/^that the artist's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
end

Given(/^that the album's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from Album;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars 
    expect(result).to eq("0")
end

Given(/^that the song's database is empty$/) do
   result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from SongDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars 
    expect(result).to eq("0")
end

Given(/^that the song's database have one song with name "([^"]*)" and duration "([^"]*)"$/) do |name, duration|
  response = RestClient.post 'http://localhost:4567/song/', { :name => name, :duration => duration }, :content_type => 'text/plain' 
  expect(response.code).to eq(201)
end


Given(/^that the artist's database have one artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |name,surname,nickname|
  response = RestClient.post 'http://localhost:4567/artist/', { :name => name, :surname => surname, :nickname => nickname }, :content_type => 'text/plain' 
  expect(response.code).to eq(201)
end

When(/^I add an artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |name,surname,nickname|
  begin
  response = RestClient.post 'http://localhost:4567/artist/', { :name => name, :surname => surname, :nickname => nickname }, :content_type => 'text/plain' 
  expect(response.code).to eq(201)
  rescue RestClient::Conflict => e  
  end 
  
end

When(/^I add an album with name "([^"]*)" and release date "([^"]*)"$/) do |title,release_date|
  response = RestClient.post 'http://localhost:4567/albums/', { :title => title, :release_date => release_date }, :content_type => 'text/plain' 
  expect(response.code).to eq(201)  
end

When(/^I search an artist with "([^"]*)" "([^"]*)" , the result should have (\d+) entry$/) do |atributo,valor,entradas|
  begin  
    String s = 'http://localhost:4567/artist/findby' + atributo + '/' + valor
    response = RestClient.get s
    if entradas != "0"
      expect(response.code).to eq(200)
    else
      expect(response.code).to eq(204)
    end
  rescue RestClient::NotFound => e
    expect(entradas).to eq("")
  end
end

When(/^I list the artists from the database , the result of the search should have (\d+) entry$/) do |arg1|
  begin
    response = RestClient.get 'http://localhost:4567/artist'
    if arg1 != "0"
      expect(response.code).to eq(200)
    end
  rescue RestClient::Conflict => e
    expect(arg1).to eq("0")
  end
end


When(/^I add a song with name "([^"]*)" and duration "([^"]*)"$/) do |name, duration|
     response = RestClient.post 'http://localhost:4567/song/', { :name => name, :duration => duration }, :content_type => 'text/plain' 
	 expect(response.code).to eq(201)
end

When(/^I search a song with "([^"]*)" "([^"]*)" , the result of the search should have (\d+) entry$/) do |atributo, valor, entradas|
  begin
      String s = 'http://localhost:4567/song/findby' + atributo + '/' + valor
      response = RestClient.get s
      if entradas != "0"
        expect(response.code).to eq(200)
      else
        expect(response.code).to eq(204)
      end
    rescue RestClient::NotFound => e
        expect(valor).to eq("")
    end
end


Then(/^the artist's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)  
end

Then(/^the album's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from Album;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)  
end

Then(/^the song's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from SongDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("1")  
end

Then(/^the entry should have name "([^"]*)" and surname "([^"]*)"$/) do |name, surname|
    resultingName = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select name from artistDB;\" -t`
    resultingName = resultingName.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingName).to eq(name)
    resultingSurname = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select surname from artistDB;\" -t`
    resultingSurname = resultingSurname.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingSurname).to eq(surname)
end

Then(/^the entry should have name "([^"]*)" and release date "([^"]*)"$/) do |title,release_date|
    resultingTitle = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select title from Album;\" -t`
    resultingTitle = resultingTitle.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingTitle).to eq(title)
    resultingReleaseDate = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select releaseDate from Album;\" -t`
    resultingReleaseDate = resultingReleaseDate.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingReleaseDate).to eq(release_date)
end


Then(/^the entry should have name "([^"]*)" and duration "([^"]*)"$/) do |arg1, arg2|
    pending
end

Given(/^that the bands' database is empty$/) do
    sleep (2)
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
end

When(/^I add a band with name "([^"]*)" and genre "([^"]*)"$/) do |name, genre|
  begin
   response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
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
    name = "Band 0"
    genre = 'Rock'
    response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
    expect(response.code).to eq(201)
    name = "Band 1"
    genre = 'Rock'
    response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
    expect(response.code).to eq(201)
    name = "Band 2"
    genre = 'Rock'
    response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
    expect(response.code).to eq(201)
    name = "Band 3"
    genre = 'Rock'
    response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
    expect(response.code).to eq(201)
    name = "Carajo"
    genre = 'New Metal'
    response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
    expect(response.code).to eq(201)

#    counter = 0
#    while counter < cant do
#        name = "Band #{counter}"
#        genre = "Rock"
#        response = RestClient.post 'http://localhost:4567/band/', { :name => name, :genre => genre }, :content_type => 'text/plain'
#        expect(response.code).to eq(201)
#        counter +=1
#    end
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from bandDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect("5").to eq(result)
end

And(/^the band with name "([^"]*)" and genre "([^"]*)" is not in bands' datebase$/) do |name,genre|
  begin  
    response = RestClient.get "http://localhost:4567/band/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(204)
  end
end

And(/^the bands' database have a band with name "([^"]*)" and genre "([^"]*)"$/) do |name,genre|
  begin
    response = RestClient.get "http://localhost:4567/band/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

Then(/^the band with name "([^"]*)" and genre "([^"]*)" should be on bands' database$/)do |name, genre|
  begin
    response = RestClient.get "http://localhost:4567/band/:#{name}"
    rescue RestClient::Conflict => e
    expect(e.response).to eq(201)
  end
end

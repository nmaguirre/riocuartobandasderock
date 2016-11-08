#encoding: utf-8

require 'rest-client'
require 'json'
require "rspec"
include RSpec::Matchers

HOST = "localhost"
PORT = "7500"

def execute_sql(sql_code)
    done = system "sh db_execute.sh \"#{sql_code}\""
    raise Exception.new("Issue executing sql code: #{sql_code}") unless done
end

Given(/^that the application has been started$/) do
      # Application is started by the setUp routines
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


Given(/^that the database contains an album named "([^"]*)" and release date "([^"]*)"$/) do |title,release_date|
  response = RestClient.post 'http://localhost:4567/albums/', { :title => title, :release_date => release_date }, :content_type => 'text/plain'
  expect(response.code).to eq(201)
end

When(/^I try to add an album named "([^"]*)" and release date "([^"]*)"$/) do |title,release_date|
  begin
  response = RestClient.post 'http://localhost:4567/albums/', { :title => title, :release_date => release_date}, :content_type => 'text/plain'
  expect(response.code).to eq(201)
  rescue RestClient::Conflict => e
  end
end

Then(/^the system informs that album named "([^"]*)" and release date "([^"]*)" already exists in the database$/) do |title,release_date|
  resultingTitle = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select title from Album;\" -t`
  resultingTitle = resultingTitle.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(resultingTitle == title)
  resultingReleaseDate = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select releaseDate from Album;\" -t`
  resultingReleaseDate = resultingReleaseDate.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(resultingReleaseDate == release_date)
end

And(/^the album's database does not change and maintain (\d+) entry$/) do |entry|
  queryResult = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from Album;\" -t`
  queryResult = queryResult.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(queryResult == entry)
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
    expect(valor).to eq("")
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
    expect(result == "1")
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


Then(/^the entry should have name "([^"]*)" and duration "([^"]*)"$/) do |name, duration|
    pending
end

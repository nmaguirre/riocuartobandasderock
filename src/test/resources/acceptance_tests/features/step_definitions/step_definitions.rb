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

Given(/^that the artist's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
end

Given(/^that the album's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from AlbumDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result=="0")
end

Given(/^that the song's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from songDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result=="0")
end

Given(/^that the song's database have one song with name "([^"]*)" and duration "([^"]*)"$/) do |name, duration|
  response = RestClient.post 'http://localhost:4567/song/', { :name => name, :duration => duration }, :content_type => 'text/plain'
  expect(response.code).to eq(201)
end

Given(/^that the song's database have one song with name "([^"]*)" and duration "([^"]*)" and belong to the album "([^"]*)"$/) do |arg1, arg2, arg3|
  pending # Write code here that turns the phrase above into concrete actions
end

Given(/^that the album's database contains an album named "([^"]*)" with the song "([^"]*)"$/) do |albumName, songName|
  pending # Write code here that turns the phrase above into concrete actions
end



Given(/^that the artist's database have one artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |name,surname,nickname|
  response = RestClient.post 'http://localhost:4567/artist/', { :name => name, :surname => surname, :nickname => nickname }, :content_type => 'text/plain'
  expect(response.code).to eq(201)
end

Given(/^that the album's database have one album with title "([^"]*)" and release date "([^"]*)"$/) do |title, release_date|
  response = RestClient.post 'http://localhost:4567/albums', { :title => title, :release_date => release_date}, :content_type => 'text/plain'
  expect(response.code).to eq(201)
end


Given(/^that the database contains an album named "([^"]*)" and release date "([^"]*)"$/) do |title,release_date|
  response = RestClient.post 'http://localhost:4567/albums', { :title => title, :release_date => release_date }, :content_type => 'text/plain'
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
  resultingTitle = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select title from AlbumDB;\" -t`
  resultingTitle = resultingTitle.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(resultingTitle == title)
  resultingReleaseDate = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select releaseDate from AlbumDB;\" -t`
  resultingReleaseDate = resultingReleaseDate.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(resultingReleaseDate == release_date)
end

And(/^the album's database does not change and maintain (\d+) entry$/) do |entry|
  queryResult = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from AlbumDB;\" -t`
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
  response = RestClient.post 'http://localhost:4567/albums', { :title => title, :release_date => release_date }, :content_type => 'text/plain'
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

When(/^I search an album with "([^"]*)" "([^"]*)" , the result of the search should have (\d+) entry$/) do |atributo, valor, entradas|
  begin
    String s = 'http://localhost:4567/albums/findby' + atributo + '/' + valor
    response = RestClient.get s
    if entradas != "0"
      expect(response.code).to eq(200)
    else
      expect(response.code).to eq(204)
    end
  rescue RestClient::NotFound => e
    expect(entradas == "")
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

When(/^I search a song with name "([^"]*)" , the result of the search should have (\d+) entry$/) do |value, entries|
  begin
      String s = 'http://localhost:4567/song/findbyname/'+ value.gsub(/[^[:print:]]|\s/,'')
      response = RestClient.get s
      if entries != "0"
        expect(response.code).to eq(200)
      else
        expect(response.code).to eq(204)
      end
    rescue RestClient::NotFound => e
        expect(e.response.code).to eq(400)
    end
end

When(/^I search a song with duration "([^"]*)" , the result of the search should have (\d+) entry$/) do |value, entries|
  begin
      String s = 'http://localhost:4567/song/findbyduration/'+ value
      response = RestClient.get s
      if entradas != "0"
        expect(response.code).to eq(200)
      else
        expect(response.code).to eq(204)
      end
    rescue RestClient::NotFound => e
        expect(e.response.code).to eq(400)
    end
end

When(/^the entry should have name "([^"]*)", duration "([^"]*)" and belongs to the album "([^"]*)"$/) do |arg1, arg2, arg3|
  pending # Write code here that turns the phrase above into concrete actions
end


Then(/^the artist's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)
end

Then(/^the album's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from AlbumDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)
end

Then(/^the song's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from SongDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
	 HEAD
    expect(result).to eq("1")
end

Then(/^the entry should have name "([^"]*)" and release date "([^"]*)"$/) do |title,release_date|
    resultingTitle = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select title from AlbumDB;\" -t`
    resultingTitle = resultingTitle.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingTitle).to eq(title)
    resultingReleaseDate = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select releaseDate from AlbumDB;\" -t`
    resultingReleaseDate = resultingReleaseDate.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingReleaseDate) == (release_date)
end


Then(/^the entry should have name "([^"]*)" and duration "([^"]*)"$/) do |name, duration|
    pending
end


When(/^I list all the albums the result of the search should have (\d+) entries$/) do |arg1|
  begin
    response = RestClient.get "http://localhost:4567/albums"
    puts("Response: "+response)
    if (arg1 == "0")
      expect(response.code).to eq(204)
    else
      expect(response.code).to eq(200)
    end
  end
end

#
#Begin Artist
#
Given(/^that the artist's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
end

Then(/^the artist's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)  
end

Given(/^that the artist's database have one artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |name,surname,nickname|
  response = RestClient.post 'http://localhost:4567/artists', { :name => name, :surname => surname, :nickname => nickname }, :content_type => 'text/plain' 
  expect(response.code).to eq(201)
end

When(/^I add an artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |name,surname,nickname|
  begin
  response = RestClient.post 'http://localhost:4567/artists', { :name => name, :surname => surname, :nickname => nickname }, :content_type => 'text/plain' 
  expect(response.code).to eq(201)
  rescue RestClient::Conflict => e  
  end   
end


When(/^I search an artist with "([^"]*)" "([^"]*)" , the result should have (\d+) entry$/) do |atributo,valor,entradas|
  begin  
    String s = 'http://localhost:4567/artists/findby' + atributo + '/' + valor
    response = RestClient.get s
    if entradas != "0"
      expect(response.code).to eq(200)
    else
      expect(response.code).to eq(204)
    end
  rescue RestClient::BadRequest => e
    expect(valor).to eq("")
  end
end

When(/^I list the artists from the database , the result of the search should have (\d+) entry$/) do |arg1|
  begin
    response = RestClient.get 'http://localhost:4567/artists'
    if arg1 != "0"
      expect(response.code).to eq(200)
    end
  rescue RestClient::Conflict => e
    expect(arg1).to eq("0")
  end
end

When(/^I search an artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" , the result is "([^"]*)"$/) do |name, surname, nickname, result|
  begin
    response = RestClient.get 'http://localhost:4567/artists/findbyallattributes/', {params: {:name=>name,:surname=>surname,:nickname=>nickname}}   
    if result == "OK"
      artist = JSON.load(response.to_str)
      artistname = artist["name"]
      artistsurname = artist["surname"]
      artistnickname = artist["nickname"]
      expect(response.code).to eq(200)
      expect(artistname).to eq(name)
      expect(artistsurname).to eq(surname)
      expect(artistnickname).to eq(nickname)
    end
    if result == "NO CONTENT"
      expect(response.code).to eq(204)
    end
    rescue RestClient::BadRequest => e
      expect(result).to eq("BAD REQUEST")
    end
end


Then(/^the entry should have name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |name, surname, nickname|
    resultingName = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select name from artistDB;\" -t`
    resultingName = resultingName.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingName).to eq(name)
    resultingSurname = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select surname from artistDB;\" -t`
    resultingSurname = resultingSurname.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingSurname).to eq(surname)
    resultingNickName = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select nickname from artistDB;\" -t`
    resultingNickName = resultingNickName.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(resultingNickName).to eq(nickname) 
end

When(/^delete this artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" , the result is "([^"]*)"$/) do |name, surname, nickname, result|
  String s = 'http://localhost:4567/artists/'
  response = RestClient.get s + 'findbyallattributes/' , {params: {:name=>name,:surname=>surname,:nickname=>nickname}}
  if result == "OK"
    expect(response.code).to eq(200)
  end
  if result == "CONFLICT"
    expect(response.code).to eq(204)
  end
  if (response.code==200)
    artistID = JSON.load(response.to_str)
    artistID = artistID["artistID"]
    begin
      response = RestClient.delete s+artistID
      if result == "OK"
        expect(response.code).to eq(200)
      end
      rescue RestClient::Conflict => e
        expect(result).to eq("CONFLICT")
    end 
  end
end

When(/^delete the artist with UUID= "([^"]*)" , the result is "([^"]*)"$/) do |artistID,result|
  begin
    String s = 'http://localhost:4567/artists/'
    response = RestClient.delete s+artistID
    rescue :: RestClient::Conflict=> e
    expect(result).to eq("CONFLICT")
  end
end

#
# BEGIN updateArtist.feature 
#

When(/^modify this artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" with new name "([^"]*)" and new surname "([^"]*)" and new nickname "([^"]*)"$/) do |name, surname, nickname, newname, newsurname, newnickname|
    String s = 'http://localhost:4567/artists/'
    response = RestClient.get s + 'findbyallattributes/' , {params: {:name=>name,:surname=>surname,:nickname=>nickname}}
    # register match
    ismatch = 0
    if response.code == 200 
    	ismatch = 1
    end

    artistID = "idnovalido"

    if ismatch==1
    	artistID = JSON.load(response.to_str)
    	artistID = artistID["artistID"]
      begin
        response = RestClient.put s+artistID, { :name => newname, :surname => newsurname, :nickname => newnickname }, :content_type => 'text/plain' 
      rescue RestClient::Conflict => e
    	   expect(response.code).to eq(200)
      end
    else
      begin
        response = RestClient.put s+artistID, { :name => newname, :surname => newsurname, :nickname => newnickname }, :content_type => 'text/plain' 
    	rescue RestClient::InternalServerError => e
    		begin
        		expect(response.code).to eq(204)
            end

      end   	
    end  
end

Then(/^the artist's database should have (\d+) entries with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |entries, name, surname, nickname|
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB where name='#{name}' and surname='#{surname}' and nickname='#{nickname}';\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(entries)
end

#
# END updateArtist.feature 
#
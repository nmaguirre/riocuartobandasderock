Given(/^that the artist's database is empty$/) do
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq("0")
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
      artistname = artist[0]["name"]
      artistsurname = artist[0]["surname"]
      artistnickname = artist[0]["nickname"]
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
When(/^modify this artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" with new name "([^"]*)" and new surname "([^"]*)" and new nickname "([^"]*)" , the result is "([^"]*)"$/) do |name, surname, nickname, newname, newsurname, newnickname, result|
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
      artistID = artistID[0]["artistID"]
      begin
        response2 = RestClient.put s+artistID, { :name => newname, :surname => newsurname, :nickname => newnickname }, :content_type => 'text/plain' 
        if response2.code == 200
          expect(result).to eq("OK")
        end
        rescue RestClient::Conflict => e
           expect(result).to eq("CONFLICT")
        end
     else
        begin
          response2 = RestClient.put s+artistID, { :name => newname, :surname => newsurname, :nickname => newnickname }, :content_type => 'text/plain' 
          rescue RestClient::BadRequest => e
            expect(result). to eq("BAD REQUEST")
        end
        
     end
 
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
    artistID = artistID[0]["artistID"]
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

Then(/^the artist's database should have (\d+) entry$/) do |arg1|
    result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB;\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(arg1)  
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

Then(/^the artist's database should have (\d+) entries with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)"$/) do |entries, name, surname, nickname|
    result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from artistDB where name='#{name}' and surname='#{surname}' and nickname='#{nickname}';\" -t`
    result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
    expect(result).to eq(entries)
end

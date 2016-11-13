
Given(/^that the bandMember's database is empty$/) do
  result = `psql -h #{HOST} -p #{PORT} -U rock_db_owner -d rcrockbands -c \"select count(*) from BandMemberDB;\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq("0")
end
Given(/^that the bandMember's database have one bandMember with artist name "([^"]*)" and artist surname "([^"]*)" and artist nickname "([^"]*)" and band name "([^"]*)"$/) do |artistName, artistSurname, artistNickname, bandName|
  response = RestClient.get 'http://localhost:4567/artists/findbyallattributes/' , {params: {:name=>artistName,:surname=>artistSurname,:nickname=>artistNickname}} 
  artistID = "idnovalido"
  if response.code == 200
    body = JSON.load(response.to_str)
    artistID = body[0]["artistID"]
  end
  #response2 = RestClient.get 'http://localhost:4567/bands/' + bandName
  #bandExist = 0
  #bandID = "idnovalido"
  #if response2.code == 200
  #  body = JSON.load(response2.to_str)
  #  bandID = body[0]["bandID"]
  #  bandExist = 1
  #end
  bandID = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select bandID from bandDB;\" -t`
  bandID = bandID.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  begin
    response3 = RestClient.post 'http://localhost:4567/bandmembers/', { :artistID => artistID, :bandID => bandID }, :content_type => 'text/plain' 
      expect(response3.code).to eq(201)
    end
end

When(/^I add an bandMember with artist name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" and band name "([^"]*)" , the result is "([^"]*)"$/) do |artistName, artistSurname, artistNickname, bandName, result|
  response = RestClient.get 'http://localhost:4567/artists/findbyallattributes/' , {params: {:name=>artistName,:surname=>artistSurname,:nickname=>artistNickname}} 
  artistExist = 0
  artistID = "idnovalido"
  if response.code == 200
    body = JSON.load(response.to_str)
    artistID = body[0]["artistID"]
    artistExist = 1
  end
  #response2 = RestClient.get 'http://localhost:4567/bands/' + bandName
  #bandExist = 0
  #bandID = "idnovalido"
  #if response2.code == 200
  #  body = JSON.load(response2.to_str)
  #  bandID = body[0]["bandID"]
  #  bandExist = 1
  #end
  bandID = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select bandID from bandDB where name=#{bandName};\" -t`
  bandID = bandID.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  begin
    response3 = RestClient.post 'http://localhost:4567/bandmembers/', { :artistID => artistID, :bandID => bandID }, :content_type => 'text/plain' 
    if response3.code == 201
      expect(result).to eq("OK")
    end
    rescue RestClient::Conflict => e
      expect(result).to eq("CONFLICT")
    end
end

When(/^I remove a bandMember with artist name "([^"]*)" and artist surname "([^"]*)" and artist nickname "([^"]*)" and band name "([^"]*)" , the result is "([^"]*)"$/) do |artistName, artistSurname, artistNickname, bandName, result|
  response = RestClient.get 'http://localhost:4567/artists/findbyallattributes/' , {params: {:name=>artistName,:surname=>artistSurname,:nickname=>artistNickname}} 
  artistExist = 0
  artistID = "idnovalido"
  if response.code == 200
    body = JSON.load(response.to_str)
    artistID = body[0]["artistID"]
    artistExist = 1
  end
  #response2 = RestClient.get 'http://localhost:4567/bands/' + bandName
  #bandExist = 0
  #bandID = "idnovalido"
  #if response2.code == 200
  #  body = JSON.load(response2.to_str)
  #  bandID = body[0]["bandID"]
  #  bandExist = 1
  #end
  bandID = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select bandID from bandDB;\" -t`
  bandID = bandID.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  begin
    response3 = RestClient.delete 'http://localhost:4567/bandmembers/' + artistID + '/' + bandID
    if response3.code == 200
      expect(result).to eq("OK")
    end
    rescue RestClient::Conflict => e
      expect(result).to eq("CONFLICT")
  end
end

When(/^I search the artist bands by artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)", the result should have (\d+) entry, and the entry is the band whit name "([^"]*)"$/) do |artistName, artistSurname, artistNickname, entries, bandName|
  begin
    response = RestClient.get 'http://localhost:4567/artists/getbands' , {params: {:name=>artistName,:surname=>artistSurname,:nickname=>artistNickname}} 
    body = JSON.load(response.to_str)
    band = band[0]["name"]
    expect(bandName).to eq(band)
  end
end

When(/^I search the artist bands by artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" , the result is "([^"]*)"$/) do |artistName, artistSurname, artistNickname, result|
  begin
    response = RestClient.get 'http://localhost:4567/artists/getbands/' , {params: {:name=>artistName,:surname=>artistSurname,:nickname=>artistNickname}} 
    if result == "NO CONTENT"
      expect(response.code).to eq(204)
    end
    if result == "OK"
      expect(response.code).to eq(200)
    end
    rescue RestClient::BadRequest => e
    expect(result).to eq("BAD REQUEST")
  end
end

Then(/^the bandMember's database should have (\d+) entries$/) do |entries|
  result = `psql -h #{HOST} -p #{PORT}  -U rock_db_owner -d rcrockbands -c \"select count(*) from BandMemberDB;\" -t`
  result = result.gsub(/[^[:print:]]|\s/,'') # removing non printable chars
  expect(result).to eq(entries)  end

Then(/^the entry should have artist name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" and band name "([^"]*)"$/) do |artistName, artistSurname, artistNickname, bandName|
  pending # Write code here that turns the phrase above into concrete actions
end

When(/^I search the artist bands by ID artist with name "([^"]*)" and surname "([^"]*)" and nickname "([^"]*)" , the result is "([^"]*)"$/) do |artistName, artistSurname, artistNickname, result|
  response = RestClient.get 'http://localhost:4567/artists/findbyallattributes/' , {params: {:name=>artistName,:surname=>artistSurname,:nickname=>artistNickname}} 
  artistExist = 0
  artistID = "idnovalido"
  if response.code == 200
    body = JSON.load(response.to_str)
    artistID = body[0]["artistID"]
    artistExist = 1
  end
  begin
    response = RestClient.get 'http://localhost:4567/artists/getbandsbyId/' + artistID 
    if result == "NO CONTENT"
      expect(response.code).to eq(204)
    end
    if result == "OK"
      expect(response.code).to eq(200)
    end
    rescue RestClient::BadRequest => e
    expect(result).to eq("BAD REQUEST")
  end
end

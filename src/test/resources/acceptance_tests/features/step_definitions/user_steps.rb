#----------------------------------------------------------------------------------------#
#
#  The file has the following order :
#     First   "Given" steps
#     Second  "When"  steps
#     Third   "Then"  steps
#     Fourth  "And"   steps
#
#----------------------------------------------------------------------------------------#
def cookies
  @cookies
end

def cookies=(c)
  @cookies = c
end

#----------------------------------------------------------------------------------------#
# => Given Steps
#----------------------------------------------------------------------------------------#

Given(/^I have successfully logged in as admin$/) do
  response = RestClient.post 'http://localhost:4567/login', name: 'admin', password: 'admin'
  self.cookies = response.cookies
  expect(response.code).to eq(200)
end

#----------------------------------------------------------------------------------------#
# => When Steps
#----------------------------------------------------------------------------------------#

When(/^I add an user with name "([^"]*)" and password "([^"]*)"$/) do |name, password|
  response = RestClient.post 'http://localhost:4567/users', name: name, password: password
  expect(response.code).to eq(200)
end

#----------------------------------------------------------------------------------------#
# => Then Steps
#----------------------------------------------------------------------------------------#

Then(/^I should be able to logout from admin account$/) do
  response = RestClient.post 'http://localhost:4567/logout', {}, { cookies: cookies }
  expect(response.code).to eq(200)
end

#----------------------------------------------------------------------------------------#
# => And Steps
#----------------------------------------------------------------------------------------#

And(/^I should be able to login as "([^"]*)" with password "([^"]*)"$/) do |name, password|
  response = RestClient.post 'http://localhost:4567/login', name: name, password: password
  expect(response.code).to eq(200)
end

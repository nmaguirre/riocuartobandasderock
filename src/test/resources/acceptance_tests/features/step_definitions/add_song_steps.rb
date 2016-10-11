#encoding: utf-8

require 'rest-client'
require 'json'
require "rspec"
include RSpec::Matchers

HOST = "loclahost"
PORT = "7500"


def execute_sql(sql_code)
        done = system "sh db_execute.sh \"#{sql_code}\""
            raise Exception.new("Issue executing sql code: #{sql_code}") unless done
end


Given(/^that the song's database is empty$/) do
  # Nothing to do here... 
end

When(/^I add a song with name "([^"]*)" and duration "([^"]*)"$/) do |arg1, arg2|
  # Nothing to do here... 
end

Then(/^the song's database should have (\d+) entry$/) do |arg1|
  # Nothing to do here... 
end

Then(/^the entry should have name "([^"]*)" and duration "([^"]*)"$/) do |arg1, arg2|
  # Nothing to do here... 
end

Feature: delete a Band

Scenario: delete a Band with name is '' 
  Given a name of band 'ska-p'
  And the band exist on the System

  When I want delete a band with name ''

  Then the API shoul'd delete of the system the band with name 'ska-p'


Scenario: delete a Band with name is '' 
  Given a name of band 'ska-p'
  And the band doesn't exist on the System

  When I want delete a band with name ''

  Then the API shoul'd return an message explaining 'the band doesn't exist on the DB'

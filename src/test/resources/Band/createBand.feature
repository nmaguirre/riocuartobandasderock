Feature: Create a Band

Scenario: Add a Band with name is ''
  Given A genre 'Rock'
  And A release '27/09/2016'
  And A list of artist ['Tito']

  When I add a band with name ''
  And genre 'Rock'
  And release '27/09/2016'
  And list of artist ['Tito']

  Then the API doesn't create a new band

Scenario: Create a Band with name 'Ska-p'
  Given A name 'Ska-p'
  And A genre 'Ska/Punk'
  And A release '24/08/1994'
  And A list of artist ['Pipi', 'Pulpul', 'Joxemi', 'Julitros', 'Kogote','Luisimi']

  When I want create a band with this parameters

  Then the API return an object with an instance with this values


Feature: Create a Band

Scenario: Add a Band with name is ''
  Given a genre 'Rock'
  And a release '27/09/2016'
  And the artist 'Tito' exists in the system
  And a list of artist ['Tito']

  When I add a band with name ''
  And genre 'Rock'
  And release '27/09/2016'
  And list of artist ['Tito']

  Then the API doesn't create a new band
  
Scenario: Add a Band with release is ''
  Given a name 'Slack'
  And a genre 'Rock'
  And the artist 'Tito' exists in the system
  And a list of artist ['Tito']

  When I add a band with release ''
  And name 'Slack'
  And genre 'Rock'
  And list of artist ['Tito']

  Then the API doesn't create a new band

Scenario: Create a Band with name 'Ska-p'
  Given A name 'Ska-p'
  And A genre 'Ska/Punk'
  And A release '24/08/1994'
  And the artists 'Pipi', 'Pulpul', 'Joxemi', 'Julitros', 'Kogote','Luisimi' exists in the system
  And A list of artist ['Pipi', 'Pulpul', 'Joxemi', 'Julitros', 'Kogote','Luisimi']

  When I want create a band with this parameters

  Then the API return an object with an instance with this values
  
Scenario: Create a Band with name 'Ska-p'
  Given A name 'Ska-p'
  And A genre 'Ska/Punk'
  And A release '24/08/1994'
  And the artists 'Pipi', 'Pulpul', 'Joxemi', 'Julitros', 'Kogote','Luisimi' exists in the system
  And A list of artist ['Pipi', 'Pulpul', 'Joxemi', 'Julitros', 'Kogote','Luisimi']
  And the album 'Incontrolable' exists in the system
  And the list of albums ['Incontrolable']

  When I want create a band with this parameters

  Then the API return an object with an instance with this values


Feature: Create a Band
  
Scenario: Create a Band with name 'Ska-p'
  Given A name 'Ska-p'
  And A genre 'Ska/Punk'
  And A release '24/08/1994'
  And A list of artist ['Pipi', 'Pulpul', 'Joxemi', 'Julitros', 'Kogote','Luisimi']

  When I want create a band with this parameters

  Then the API return an object with an instance with this values


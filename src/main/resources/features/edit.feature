jhFeature : Edit a transaction
    As a user
    I want to edit transaction

Background :
    Given a transaction food with 50 income at 2018-11-07

Scenario: Edit amount
    When I edit amount to 500
    Then transaction is food with 500 income at 2018-11-07

Scenario: Edit date
    When I edit date to 2018-11-06
    Then transaction is food with 50 income at 2018-11-06

Scenario: Edit note
    When I edit note to stationary
    Then transaction is stationary with 50 income at 2018-11-07

Scenario: Edit type
    When I edit amount to -50
    Then transaction is food with 50 expense at 2018-11-07
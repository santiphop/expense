Feature : Add a transaction
    As a user
    I want to add income and expense

Background :
    Given a user with balance 200 exists

Scenario: Deposit
    When I deposit 50
    Then my account have balance 250 exists

Scenario: Expense
    When I expense 100
    Then my account have balance 100 exists

Scenario: Expense more than Balance
    When I expense 250
    Then my account have balance -50 exists
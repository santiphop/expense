Feature : Deposit
    As a customer
    I want to deposit to my account using ATM

Background :
    Given a customer with no balance

Scenario : Deposit amount
    When I deposit 500 to my account
    Then my account balance is 500

Feature: Add and View
    As a user
    I can add and view income and expense
    so that I know how much money I have

Background:
    Given a customer with balance 200 exists

Scenario: Transact amount more than 0
    When I transact
@orderrequest
Feature: test Return Order Request feature

  Background:
    Given navigate to loan estimate application

  @loanrequest
#  Scenario: test how much loan need based on Income and Expenses
#    Then enter following details
#      | AppType | No_of_dependants | buy_property    | Income | Other_income | Expenses | Loan_repayment | Other_loan_repayment | Commitments | Credit_card_limit |
#      | Single  | 0                | Home to live in | 80000  | 10000        | 500      | 0              | 100                  | 0           | 10000             |
#    When click on how much I could borrow
#    Then Asert estimate you borrow should be "$479,000"

  Scenario: test clear the form
    Then enter following details
      | AppType | No_of_dependants | buy_property    | Income | Other_income | Expenses | Loan_repayment | Other_loan_repayment | Commitments | Credit_card_limit |
      | Single  | 0                | Home to live in | 80000  | 10000        | 500      | 0              | 100                  | 0           | 10000             |
    When click on how much I could borrow
    Then validate start over button appears or not
    Then click on start over button
    Then validate the form is cleared or not

#  Scenario: Entering only $1 for Living expenses, and leaving all other fields as zero
#    When enter dollar "1" for Living expenses and other fields are zero
#    When click on how much I could borrow
#    Then validate the required message appeared correctly or not





Feature: Book Application

Background: User generates token for authorization
Given I am an athorized user

Scenario: An authorized user can add and delete the books

Given A list of books is available
When User adds book to reading list
Then book is added successfully
When user removes book from the list
Then book is removed successfully

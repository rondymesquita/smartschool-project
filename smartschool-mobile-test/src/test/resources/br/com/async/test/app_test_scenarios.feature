Feature: AppTest

@device
Scenario: Test AppTest basic flow
	Given I open AppTest
	And I fill name with "Rondy"
	And I fill music with "Metal"
	When I click on send button
	Then I should see "Rondy likes Metal"

#@web	
#Scenario: Test webview navigation
#	Given I open "http://www.bing.com"
#	And I enter "Darth Vader" in search field
#	When I click on search button
#	Then I should see on page "Darth Vader"
	


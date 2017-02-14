# Addresses-api
API to query for addresses based on their Eircode

To run the project there are 3 different maven profiles:

# For local testing with eclipse IDE and mocked services
mvn clean install -Plocal

# For jar generation with included tomcat server at port 8080
mvn clean install package -Pjar.execution

# For jar generation with included tomcat server at port 8080 and integrated with the API
mvn clean install package -Pintegrated

# Other considerations
1.- You will need your own API Key to be included in pom.xml in integrated profile: <alliescomputing.api.key></alliescomputing.api.key>
2.- Other properties can be used to change testing URLs (in alliescomputing.properties file)

    alliescomputing.api.scheme=@alliescomputing.api.scheme@
    alliescomputing.api.hostname=@alliescomputing.api.hostname@

    # Services Parameters
    alliescomputing.api.address.url=@alliescomputing.api.address.url@
    alliescomputing.api.geo.url=@alliescomputing.api.geo.url@
    alliescomputing.api.position.url=@alliescomputing.api.position.url@
    alliescomputing.api.geo.reverse.url=@alliescomputing.api.geo.reverse.url@

3.- You will have to configure an ehcache.xml file where you will define where would you want to have the ehcache persisted in disk
	<diskStore path="src/main/resources/cache" />
4.- Cache configurations has not been tuned, it will require loadtesting and it will depend on many factors to find out the proper configuration.


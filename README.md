# Egree4j - Java library for Egree

[![Build Status](https://travis-ci.org/northbyte/egree4j.svg?branch=master)](https://travis-ci.org/northbyte/egree4j)

This is a Java library to easily integrate the digital signature service [Egree](https://egree.com/) with your java application. It translates the Egree web service calls to Java, which allows any java user to invoke the calls with minimal configuration of the HTTP/Security/Translations around it.

The library is built for the v2 of their API.

Currently the library supports all of the current endpoints in the Egree service.

## Notice

You will still need a subscription with the Egree service!

Since the v2 of the Egree service is still under beta it can change without notice. This means that there might be occasions when the client needs to be updated but me not knowing it. Drop me a message or a pull request if so.

## Installation

Using Maven 3.2.1, clone the repository and run

    mvn clean install

This will install the current snapshot version working with the latest version of the Egree service. Then you can include it in your project.

Maven:

    <dependency>
        <groupId>com.github.northbyte</groupId>
        <artifactId>egree4j</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

Gradle:
    
    compile 'com.github.northbyte:egree4j:1.0.0-SNAPSHOT' 

SBT:

    libraryDependencies += "com.github.northbyte" %% "egree4j" % "1.0.0-SNAPSHOT"

## Configuration

To be able to work with the Egree service you need to authorize yourself using the API keys generated from Egree. The Egree4j will look for configuration settings passed as parameters to the service or in the egree4j.properties file.

The minimum required settings is:

    egree4j.auth.key=
    egree4j.auth.secret=
    
As such, you can either pass the parameters when you start your application:

    java -jar yourapp.jar -Degree4j.auth.key=someKey -Degree4j.auth.secret=someSecret

Or you can in for example your java code set it:

    System.setProperty("egree4j.auth.key", "someKey");
    System.setProperty("egree4j.auth.secret", "someSecret");
    
    Egree egree = EgreeFactory.getInstance();
 
By far the most convenient way is to have it in a egree4j.properties file that you can stow away in your your .war-file or on disk.


## Usage


Everything in the library revolves around the Egree service interface. A caller can obtain it by

    Egree egree = EgreeFactory.getInstance();

Once this has been initialized calling the Egree service is the same as calling the related method in the Egree interface. Using the obtained Egree class we can then create, get, edit or delete cases. Some examples.

Searching for Cases in the Egree service that you have access to:

    // A full text search in Sent state
    Query query = new FulltextQuery("My recent case");
    query.setStatus(Status.SENT);
    List<Case> cases = egree.searchCases(query);
    
    // Searching for cases after 2014-01-01 and containing
    // meta data information about open licensing previously
    // put on the case
    MetadataQuery metaQuery = new MetadataQuery();
    metaQuery.setFromDate(new DateTime(2014, 01, 01, 12, 00));
    metaQuery.getMetadata().put("licensing", "open");
    List<Case> cases = egree.searchCases(metaQuery);
    
Whenever we want to create a case we have to either create it by templates or we can create them and push them to Egree. A Case consists of at least one Party and one Document. These can be either done by pushing the whole document with its data in a DataDocument or by only pushing the document hash in a HashDocument. HashDocuments are generally used when the document is secret/protected but a signature is still required.

Creating a case is done by creating a new Draft:

    // Create a new Case by using a Draft
    Draft newCase = new Draft("My case name");
    
Then we can just append the data we want:

    // Create the party
    Party party = new Party();
    party.setEmailAddress("john.doe@example.com");
    party.setName("John Doe");
    party.setCulture(Culture.EN);
    
    // Create the document
    File f = new File("/home/me/HouseContract.pdf");
    Document document = new DataDocument("HouseContract.pdf", f);
    document.setType(DocumentType.ORIGINAL);
   
    // Add the party and document
    newCase.getParties().add(party);
    newCase.getDocuments().add(document);
    
    // Push it to Egree. Once this is done, the Draft instance
    // is no longer needed. 
    egree.createCase(newCase);
    
    // The id of the Draft is always generated. This means you
    // can use it to find the stored Case
    Case storedNewCase = egree.getCase(newCase.getId());

Note that data processing is normally done automatically unless set by the caller. This includes setting the data size and performing a simple ContentType evaluation of the file. If unsure the caller should set this manually.

Single sign-on for an already created Agent:

    // Either the user name or their e-mail works
    // The callback url can refer to your page/application
    egree.singleSignOn("some.user@example.com", "http://example.com/callback");
    



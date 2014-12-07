Egree4j - Java library for Egree
================================

This is a Java library to easily integrate the digital signature service 
<a href="https://egree.com/">Egree</a> with your java application. It translates
the Egree web service calls to Java, which allows any user to invoke the calls
without configuring the HTTP/Security/Translations around it.

The library is built for the v2 of their API.

Currently the library supports all of the current endpoints in the Egree
service.

Notice
------

You will still need a subscription with the Egree service!

Since the v2 of the Egree service is still under beta it can change without
notice. This means that there might be occasions when the client needs to be
updated but me not knowing it. Drop me a message or a pull request if so.

Installation
------------

Using Maven 3.2.1, clone the repository and run

    mvn clean install

This will install the current snapshot version working with the latest version
of the Egree service.

Configuration
-------------

To be able to work with the Egree service you need to authorize yourself using
the API keys generated from Egree. The Egree4j will look for configuration
settings passed as parameters to the service or in the egree4j.properties file.

The minimum required settings is:

    egree4j.auth.key=
    egree4j.auth.secret=

Usage
-----

TODO
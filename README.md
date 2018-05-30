# SkillTracker-App

Spring

1.  Download Spring Tool Suite from https://spring.io/tools/sts/all
2.  Install Apahce Maven
3.  Import project in STS
4.  Run as a Spring Boot App

Solr

1.  Download and install Java 8+ if not done already
2.  Download apache solr 7.3.1
3.  Unpack downloaded zip
4.  Navigate to bin folder
5.  Run `solr start`
6.  Run `solr create -c <core-name>`. This will create a folder in your solr directory `\server\solr\<core-name>`
7.  Create a folder in the root solr directory to store necessary .jar files. ie) `.\jarfiles`
8.  In directory `./dist` copy `solr-dataimporthandler-7.3.1.jar` and `solr-dataimporthandler-extras-7.3.1.jar` and paste in previously created directory.
9.  Download mySQL Connector/J from https://dev.mysql.com/downloads/connector/j/5.1.html
10. Copy and paste the extracted `*-bin.jar` file in the previously created directory.
11. Navigate to `\server\solr\<core-name>\conf\solrconfig.xml` and
    add
    ```xml
    <lib dir="../../../jarfiles" regex=".*\.jar" />
    ```
    and
    ```xml
    <requestHandler name="/dataimport" class="org.apache.solr.handler.dataimport.DataImportHandler">
        <lst name="defaults">
            <str name="config">db-data-config.xml</str>
        </lst>
    </requestHandler>
    ```
    within the `<config>` tag
12. Create a `db-data-config.xml` file in `./conf` and paste with correct, password, etc..
    ```xml
    <dataConfig>
        <dataSource driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/skill_tracker" user="root" password="************" />
        <document>
            <entity name="employee" query="SELECT ID, FN, LN, DOB, DOJ, CL, ADDRESS, CITY, STATE, ZIPCODE, CREATED, UPDATED from employee">
                <field column="ID" name="Id" />
                <field column="FN" name="FirstName" />
                <field column="LN" name="LastName" />
                <field column="DOB" name="DateOfBirth" />
                <field column="DOJ" name="DateOfJoin" />
                <field column="CL" name="CareerLevel" />
                <field column="ADDRESS" name="Address" />
                <field column="CITY" name="City" />
                <field column="STATE" name="State" />
                <field column="ZIPCODE" name="ZIPCode" />
                <field column="CREATED" name="Created" />
                <field column="UPDATED" name="Updated" />
            </entity>
        </document>
    </dataConfig>
    ```
13. In `./managed-schema` add field tags with correct fields
    ```xml
    <field name="Id" type="text_general" indexed="true" stored="true" required="true" />
    <field name="FirstName" type="text_general" indexed="true" stored="true"/>
    <field name="LastName" type="text_general" indexed="true" stored="true" />
    <field name="DateOfBirth" type="text_general" indexed="true" stored="true"/>
    <field name="DateOfJoin" type="text_general" indexed="true" stored="true"/>
    <field name="CareerLevel" type="text_general" indexed="true" stored="true"/>
    <field name="Address" type="text_general" indexed="true" stored="true"/>
    <field name="City" type="text_general" indexed="true" stored="true"/>
    <field name="State" type="text_general" indexed="true" stored="true"/>
    <field name="ZIPCode" type="text_general" indexed="true" stored="true"/>
    <field name="Created" type="text_general" indexed="true" stored="true"/>
    <field name="Updated" type="text_general" indexed="true" stored="true"/>
    ```
14. Open browser, and go to http://localhost:8983/solr/#/
15. To stop solr, run `solr stop -all`
16. To restart solr, run `solr start`

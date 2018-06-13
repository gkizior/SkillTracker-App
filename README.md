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
        <dataSource driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/skill_tracker" user="root"  password="*************" />
        <document>
            <entity name="employee" query="SELECT ID, FN, LN, DOB, DOJ, CL, ADDRESS, CITY, STATE, ZIPCODE, CREATED, UPDATED FROM employee">
                <field column="ID" name="Id" />
                <field column="FN" name="firstName" />
                <field column="LN" name="lastName" />
                <field column="DOB" name="dateOfBirth" />
                <field column="DOJ" name="dateOfJoin" />
                <field column="CL" name="careerLevel" />
                <field column="ADDRESS" name="address" />
                <field column="CITY" name="city" />
                <field column="STATE" name="state" />
                <field column="ZIPCODE" name="zipcode" />
                <field column="CREATED" name="created" />
                <field column="UPDATED" name="updated" />
                <entity name="skill" pk="SKILLID" query="SELECT * FROM skill WHERE ID='${employee.ID}'">
                    <field column="SKILL" name="skills" />
                </entity>
            </entity>
        </document>
    </dataConfig>
    ```
13. In `./managed-schema` add field tags with correct fields
    ```xml
    <field name="Id" type="text_general" indexed="true" stored="true" required="true" multiValued="false"/>
    <field name="firstName" type="text_general" indexed="true" stored="true"/>
    <field name="lastName" type="text_general" indexed="true" stored="true" />
    <field name="dateOfBirth" type="text_general" indexed="true" stored="true"/>
    <field name="dateOfJoin" type="text_general" indexed="true" stored="true"/>
    <field name="careerLevel" type="text_general" indexed="true" stored="true"/>
    <field name="address" type="text_general" indexed="true" stored="true"/>
    <field name="city" type="text_general" indexed="true" stored="true"/>
    <field name="state" type="text_general" indexed="true" stored="true"/>
    <field name="zipcode" type="text_general" indexed="true" stored="true"/>
    <field name="created" type="text_general" indexed="true" stored="true" multiValued="false"/>
    <field name="updated" type="text_general" indexed="true" stored="true"/>
    <field name="skills" type="string" indexed="true" stored="true" multiValued="true"/>
    
    <fieldType name="word_concate" class="solr.TextField" indexed="true" stored="false">
    	<analyzer>
        	<charFilter class="solr.PatternReplaceCharFilterFactory" pattern="\s*" replacement=""/>
            <tokenizer class="solr.StandardTokenizerFactory"/>
        </analyzer>
    </fieldType>
    <field name="cfname" type="word_concate"/>
    <copyField source="careerLevel" dest="cfname"/>
    <field name="skillsNoSpaces" type="word_concate" multiValued="true"/>
    <copyField source="skills" dest="skillsNoSpaces"/>
    ```
14. Open browser, and go to http://localhost:8983/solr/#/
15. To stop solr, run `solr stop -all`
16. To restart solr, run `solr start`

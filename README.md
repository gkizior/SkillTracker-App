# SkillTracker-App

Spring

1. Download Spring Tool Suite from https://spring.io/tools/sts/all
2. Install Apahce Maven 
3. Import project in STS
4. Run as a Spring Boot App

Solr

1. Download and install Java 8+ if not done already
2. Download apache solr 7.3.1
3. Unpack downloaded zip
4. Navigate to bin folder
5. Run ``solr start -e cloud``
6. You can select the number of nodes, port, collection name, shard count, replica per shard count, and configuration - or just <Enter> through the prompts for default options
7. Open browser, and go to http://localhost:8983/solr/#/
8. Add fields to collection schema
9. To restart after closing ``solr restart -e cloud``

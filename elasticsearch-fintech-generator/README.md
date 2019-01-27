elasticsearch-fintech-generator
========
Helps you build data for a financial services use case.

Usage
=====
* create keystore by executing the build_keystore.bash (arguments are password, elasticsearch host without protocol, port)
* update src/main/resources/config.properties to include critical configurations
* build clean; build fatJar
* java -jar build/libs/elasticsearch-fintech-generator-<VERSION>.jar <number of threads> <type of workload>

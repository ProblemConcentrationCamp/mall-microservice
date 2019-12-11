# mall main repository

## how to run this project
1. run `git config --global core.autocrlf false` before commit to git(this command can make the LF not be replaced by 
CRLR when you commit to github on the window)
2. clone the [mall-dependencies](https://github.com/ProblemConcentrationCamp/mall-dependencies) 
(this project's effect is the same as spring-boot-dependencies)
3. open the mall-dependencies and then run `mvn clean install`
4. open the idea, select the `Open`, then select the path of this repository
5. open the module, select the parent's pom, click right-hand button, select `Run as Maven Project`
6. if your idea no install the lombok plugin, please install it;

## ide setting
1. set the tab is 4 space
2. set the line separator is Unix and macOS(\n)
3. set the max wrap on typing is 120

## System Environment
1. JDK 11
2. Mysql 8.0
3. SpringBoot 2.2.2.RELEASE
4. SpringCloud Hoxton.RELEASE



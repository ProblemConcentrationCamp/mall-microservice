# common-starter

## 1. Transaction
open transaction
> 1. the `@EnableTransactionManagement` must be set on the main class
> 2. set the `app.transaction.enable` to true 
> 3. set the `app.transaction.advisor-expression(the transaction aop)`
> 4. the application must have data source, such as druid, HikariCP etc.

## 2. Security
the security is default open, if you have no need of it, you canexport the SecurityAutoConfiguration : 
`@SpringBootApplication(exclude = SecurityAutoConfiguration.class)`

open security
>1. set the `app.transaction.enable` to true 
>2. set the `app.security.ant-matchers` to config the url that can no auth
>3. declare a class implements `UserDetailsService`
 

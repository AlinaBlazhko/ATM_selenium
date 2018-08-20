# ATM_selenium

SOLID principles

|Class|Problem|Solution|
|-----|-------|---------|
|pages|All fields is public and OCP is broken| Do all fieldsprivate|
|pages|SRP is broken. We have many pages wich includes the same methods| Create abstract class. Each page could extends this class|
|Test classes|all assesrtions was into test methods.ISP is brokes| CheckThat class with all assertions was created|

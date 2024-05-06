## How?

    1. project settings(for nested query)
    2. spring-data-elasticsearch build → create jar file → apply to project
    3. improvement nested query



## Solution Plan

---

### Problem

→ Applying nested queries for simple same paths has performance issues

![image](https://github.com/Khu-opensource/nested-improvement/assets/87745916/5db54eec-26a6-4d5f-9d00-ceec7109e717)

- In diagram flow of how nested works to understand where to apply custom query in the process    
![image](https://github.com/Khu-opensource/nested-improvement/assets/87745916/2e89ada5-2e10-434b-b695-37b16fba0e06)
    
- Custom query example 
```java
      Criteria criteria = new Criteria("characterDocuments.name").is(name).nested(true);
      criteria = criteria.add(new Criteria("characterDocuments.name").is(side).nested(true);
```
- Explane
  - default value is false
  - criteria objects set to true generate one query for the same path
  - In our existing code, we only apply nested at a higher level for queries that are processed by an `and` operation at a time, such as "Seoul + Gangnam-gu", without touching the cases where a user wants to apply a single path to multiple nested.

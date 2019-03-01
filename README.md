# CS 425 - Database Orgaization

This repository contains assignments and project for CS425 during the term of Fall 2017

# Project details

The enterprise is a retailer, such as a department store, discount store, supermarket, convenience store, etc. issues of employees, corporate finance, etc. are ignored, and focus is on the retail sales activities.

The retailer sells a large variety of products at multiple stores. Not all products are at all stores. Pricing may be different at different stores. Each store has its own inventory of products and needs to decide when to reorder and in what quantity. Customers may identify themselves by joining the frequent-shopper program. Others may remain anonymous. The retailer has a Website that accepts orders. From a database perspective, it is just a special store that has no physical location and has no anonymous customers. The database tracks inventory at each store, customer purchases (by market basket and by customer, where possible), sales history by store, etc. Various user interfaces and applications access the database to record sales, initiate reorders, process new orders that arrive, etc.

The application should support the following actions:
1. Every customer registers an account with an email and will be assigned an ID for the frequent-shopper program. For simplifying the case, similar to CostcoMembership, every customer has to register an account before purchasing the products in store.
2. A customer holding an account can register/modify/delete credit cards and addresses for their account in store.
3. A customer can check out his/her order in store.
4. Employees can check and update the products/stock information. Update happens when products are sold to the store by the vendor(s).
5. When new stock is added to a store, the system checks that if the total size of all products stored in this store exceeds the size of the store (warehouse).

For more details go to the [Project walkthrough](project/Project%20walkthrough.pdf)

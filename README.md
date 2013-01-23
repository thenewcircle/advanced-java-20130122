advanced-java-20130122
======================

Class Design
------------

"Value class" recipe:

* private final fields
* constructor
* getters (and setters if absolutely necessary)
* hashCode/equals (implemented using only immutable state)

Immutability
------------

* safer class inheritance: easier to reason about polymorphism
* safe structural (value) identity
* sometimes provides performance benefits due to sharing
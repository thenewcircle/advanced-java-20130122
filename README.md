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

Concurrency
-----------

* using built-in concurrency primitives (synchronized, wait/notify) is too hard
* implement the producer-consumer pattern using BlockingQueue
* don't reinvent the wheel: use akka!

Videos
------

Day 1:

* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/1.1.mov
* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/1.2.mov
* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/1.3.mov
* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/1.4.mov

Day 2:

* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/2.1.mov
* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/2.2.mov
* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/2.3.mov
* https://mrkn.s3.amazonaws.com/recordings/advanced-java-20130122/2.4.mov
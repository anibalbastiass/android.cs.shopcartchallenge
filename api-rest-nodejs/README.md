# Cornershop Android Development Test

## Before you begin
* You will need to fork this project and invite the following users as collaborators: @DevPicon @jhonnyx2012 and @coyarzun89

## The test
Create a simple Android app for counting things. You'll need to meet high expectations for quality and functionality. It must meet at least the following:

* Add a named counter to a list of counters.
* Increment any of the counters.
* Decrement any of the counters.
* Delete a counter.
* Show a sum of all the counter values.
* Persist data back to the server.
* Must **not** feel like a learning exercise. Think you're building it to publish on Google Play.

Some other notes:

* Concern for the UI of the app is **essential**. Stick to Material Design guidelines.
* Showing off the knowledge of mobile architectures is essential.
* Unreliable networks are a thing. Handle errors.
* Offer support to Android API >= 16.
* Create incremental commits instead of a single commit with the whole project
* **Test your app to the lastest Android API**

Extra points:

* Unit tests are good.
* Don't bloat your Activities/Fragments.
* Minimal use of external dependencies.


A possible app design could be:
(the design is all up to you, this is just an example)

```
+-----------------------------+
| Counters                [+] |
+-----------------------------+
+-----------------------------+
| Cups of tea        [-] 5 [+]|
| Boats I've been on [-] 1 [+]|
| White shirts       [-] 9 [+]|
+-----------------------------+
+-----------------------------+
|                   Total: 15 |
+-----------------------------+
```

**Remember**: The UI is super important. Don't build anything that doesn't feel right for Android.


## Install and start the server

```
$ npm install
$ npm start
```

## API endpoints / examples

> The following endpoints are expecting a `Content-Type: application/json`

```
GET /api/v1/counters
# []

POST {title: "bob"} /api/v1/counter
# [
#   {id: "asdf", title: "bob", count: 0}
# ]

POST {title: "steve"} /api/v1/counter
# [
#   {id: "asdf", title: "bob", count: 0},
#   {id: "qwer", title: "steve", count: 0}
# ]

POST {id: "asdf"} /api/v1/counter/inc
# [
#   {id: "asdf", title: "bob", count: 1},
#   {id: "qwer", title: "steve", count: 0}
# ]

POST {id: "qwer"} /api/v1/counter/dec
# [
#   {id: "asdf", title: "bob", count: 1},
#   {id: "qwer", title: "steve", count: 2}
# ]

DELETE {id: "qwer"} /api/v1/counter
# [
#   {id: "asdf", title: "bob", count: 1}
# ]

GET /api/v1/counters
# [
#   {id: "asdf", title: "bob", count: 1},
# ]
```

> **NOTE:* Each request returns the current state of all counters.

Remember, you can't write bugs if you don't code at all.

![Roll Safe](http://i3.kym-cdn.com/entries/icons/original/000/022/138/reece.JPG)

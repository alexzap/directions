# Directions and Geocode App

#### Tasks required to make app production ready :
- The ability for the client to select their required departure or arrival times, as well as have backend return estimated time of arrival
- Show traffic information for the route to the client
- Add ability to add waypoints along route
- Change backend DB to a more permanent solution such as MySQL or Postgres
- A lot more front end polish

#### Issues encountered
Some of the issues I had was how to deal with parsing the address information from the reverse geocode lookup.
Most of the time, several results are returned and the first one may be missing the suburb while the others are missing the most accurate street address information.
Another issue was when trying to work with getting the front end changes working. I had never worked with the vue.js framework so I needed to research how it worked.
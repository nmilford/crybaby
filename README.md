# crybaby: events dashboard.

This is my first stab at clojure and is primarily a learning project.

crybaby will be, once I write it, a system that will accept an event description and a timestamp, log it to a data store and display it in a searchable web app.

This is not meant to replace syslog, but for me to add a curl/REST call to certain triggers in my environment for light auditing.

For example, I could add a line that curl's against crybaby everytime tomcat is restarted with a message like "Tomcat restarted by $user on $server at $time."

## Usage

Too early to tell, will look something like:

curl "http://crybaby.example.com/api/v1/event" -v -X PUT -d "#Tomcat restarted on #server1 by #nathan" 

## License

Copyright (C) 2012 Nathan Milford

Distributed under the Apache License, Version 2.0.

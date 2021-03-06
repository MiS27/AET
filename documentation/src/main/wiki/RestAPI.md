### REST API

Representational State Transfer API for accessing and modifying data stored in AET Database. REST API is part of AET System and is the interface between system database, user and application.

Its methods are used by *AET Maven Plugin* to download reports, *HTML Report* uses it to load images and to perform rebase action.  

Rebase (switching artifacts id of pattern(s)) and adding comments should be done on client side. REST API only consumes whole json representation of suite.  

#### REST API HTTP methods

Base api path:

`http://<Domain_or_IP_Address>:<PORT>/api`

##### Get artifact by artifact Id
* **URL**: `/artifact`
* **HTTP Method**: GET
* **Parameters**: `company`, `project`, `id`
* **Example**: http://aet.example.com/api/artifact?company=cognifide&project=example&id=56fa80c1ab21c61f14bfef45
* **Description**: Returns file from DB as `application/octet-stream` (even for json files), if not found or error occurred json message with `application/json` content type is returned.

--------
##### Get metadata by correlationId
* **URL**: `/metadata`
* **HTTP Method**: GET
* **Parameters**: `company`, `project`, `correlationId` 
* **Example**: http://aet.example.com/api/metadata?company=cognifide&project=example&correlationId=cognifide-example-1459257500567 
* **Description**: Returns newest version of metadata identified by provided correlationId.\

--------
##### Get metadata by suite name
* **URL**: `/metadata`
* **HTTP Method**: GET
* **Parameters**: `company`, `project`, `suite` 
* **Example**: http://aet.example.com/api/metadata?company=cognifide&project=example&suite=mysimplesuite 
* **Description**: Returns newest version of latest run (identified by latest correlationId) of metadata by with provided suite name.

--------
##### Update suite metadata
* **URL**: `/metadata`
* **HTTP Method**: POST
* **Parameters**: raw JSON in POST body
* **Example**: http://aet.example.com/api/metadata `[raw json in post body]`
* **Description**: This method increments version number before saving to DB and returns updated suite object in json format. Returns status 409 if given suite is locked.

--------
##### Get lock for suite
* **URL**: `/lock`
* **HTTP Method**: GET
* **Parameters**: `company-project-name` as last part of path 
* **Example**: http://aet.example.com/api/lock/cognifide-example-mysimplesuite 
* **Description**: Returns lock status for given suite (true if it's locked or false in json)

--------
##### Try to set lock
* **URL**: `/lock`
* **HTTP Method**: POST
* **Parameters**: `value` - additional info for lock (currently it's correlationId only) "company-project-name" as last part of path 
* **Example**: http://aet.example.com/api/lock/cognifide-example-mysimplesuite `[value=cognifide-example-mysimplesuite-12312454]`
* **Description**: This methods sets lock only if there is no lock already set for given suite. Returns status 409 if given suite is already locked.

--------
##### Update heart beat
* **URL**: `/lock`
* **HTTP Method**: PUT
* **Parameters**: `value` - additional info for lock (currently it's correlationId only) "company-project-name" as last part of path 
* **Example**: http://aet.example.com/api/lock/cognifide-example-mysimplesuite `[value=cognifide-example-mysimplesuite-12312454]` 
* **Description**: This method extends the duration of a lock for given suite.
 
--------
##### Gets list of all suites in system
* **URL**: `/config/list`
* **HTTP Method**: GET
* **Example**: http://aet.example.com/api/config/list 
* **Description**: Returns all suites for all projects in all companies as html list of links to reports and metadata (this method will change or will be removed in near future- for now it stays only for devs and testing purposes).
 
--------
##### Get all locked suites
* **URL**: `/config/locks`
* **HTTP Method**: GET
* **Example**: http://aet.example.com/api/config/locks 
* **Description**: Returns list of current locks.
 
--------
##### Get communication settings
* **URL**: `/config/communicationSettings`
* **HTTP Method**: GET
* **Example**: http://aet.example.com/api/config/communicationSettings 
* **Description**: Returns current JMS broker settings and report app domain. This method is used by maven client. 
# Simple overview about k6 for performance testing

 This is just an example about how I could apply [k6](https://k6.io/) for performance testing (with a noob's eyes) since I don't have experience enough to provide analisys and recommendations about it.

### JS Project Structure
```Gherkin
src
  + endpoints                The url for every service
  + requests                 Requests that will be sent to API services
  + tests                    Runners
  + utils                    Utility funtions
```

This project has only one test for "findPetByStatus" service in order to demo how I could apply a tool like k6 for this purposes.
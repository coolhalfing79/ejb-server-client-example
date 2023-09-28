# ejb-server-client-example

This repository has two examples one that acts as a server for ejb remote beans and a client

## Running the examples

### Building the ejbserver project
Run the maven build for the ejbserver project and install the artifacts in the local maven repository. This is important and should be done before running
 the maven build for the client project since the client project uses ejbserver-ejb as a dependency.
```bash
mvn install
```

Create a container using docker or podman
```bash
podman build -t ejbserver .
```

### Building the ejbclient project
Run the maven build for the ejbclient project.
```bash
mvn package
```

Create a container using docker or podman
```bash
podman build -t ejbclient .
```

### Running the projects
1. Create the network for the container to share
```bash
podman network create corbanet 
```
2. Run the ejbserver
```bash
podman run --rm -p 9080:9080 -p 2819:2819 -p 2820:2820 --name ejbserver --net corbanet ejbserver
```
Exposing the CORBA ports is not strictly required these are exposed only to allow the client to connect using your machine's ip

3. Run the ejbclient
```bash
podman run --rm -it --net corbanet -e CORBA_HOST="ejbserver" -e CORBA_PORT="2819" ejbclient
```
alternatively

```bash
podman run --rm -it --net corbanet -e CORBA_HOST="<ip of your machine>" -e CORBA_PORT="2819" ejbclient
```

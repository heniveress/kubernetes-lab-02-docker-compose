# Microservices and Kubernetes: Lab 2 - Docker Compose and deployment

In Lab 2 you will deploy a Docker Compose service in a live environment. For the purposes of this lab, for the duration of the lab on Friday you will be given a Hetzner environment where you can experiment with this setup.

You have two choices:
- In case you've finished [Lab 1](https://github.com/darktohka/kubernetes-lab-02-docker), you can choose to extend your Docker container with a docker-compose.yaml environment:
  1. First, create the corresponding docker-compose.yaml environment that will start your Docker container, as if you were starting it with a simple Docker command.
  2. You can test with `docker compose up -d`, or shut down your environment with `docker compose down`.
  3. Add an external database to your code that persists data over the previous in-memory storage mechanism.
  4. Create two new networks: one for your backend and one for your database. Connect your backend to both networks, but connect the database only to the database network.
  5. Add the new database to the docker-compose, and have the application connect to it through its **container name**.
- You can also choose to install any self-hosted application that is packaged using a Dockerfile. For example, try [Immich](https://docs.immich.app/install/docker-compose), an awesome Google Photos alternative.

Try to deploy the application in the live environment!

If possible, set up Caddy as your web server.

Fork this repository and continue your work here.

---

**NOTE:** Could not deploy to a live environment because of the limit I did not get access to a server (Hetzner) in class. Everything else was done and tested locally.

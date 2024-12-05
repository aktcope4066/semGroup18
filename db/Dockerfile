# Use the latest MySQL image
FROM mysql:8.3

# Set the working directory
WORKDIR /tmp

# Copy SQL files to the working directory of the container
COPY *.sql /tmp/

# Copy the main SQL file to docker-entrypoint-initdb.d.
# Scripts and SQL files in this folder are executed on container startup.
COPY world.sql /docker-entrypoint-initdb.d

# Set the root password
ENV MYSQL_ROOT_PASSWORD=group18

# Optional: Set the database to be created at startup
ENV MYSQL_DATABASE=world

# You can set the authentication plugin directly in a custom configuration file
# Remove any invalid CMD with --default-authentication-plugin
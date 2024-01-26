# MongoDB Installation Instructions

Download MongoDB community edition from [MongodB Download Page](https://www.mongodb.com/try/download/community) for your relevant system

## MacOS

1. Extract the Tarball file using

  `tar -zxvf mongodb-macos-arm64-7.0.tgz`

2. Ensure binaries are in a directory listed in `PATH` environment variable by doing either of the following:

   Copy binaries into directory listed in `PATH` variable via

          `sudo cp /path/to/the/mongodb-directory/bin/*  /usr/local/bin`

   Create a symbolic link to binaries from a directory listed in your `PATH` variable via

           `sudo ln -s /path/to/the/mongodb-directory/bin/* /usr/local/bin/`
  
3. Create data directory wherever the data for the database should be stored:

          `sudo mkdir -p ~/path/to/directory/data/db`

4. Create log directory under the data directory

          `sudo mkdir -p ~/path/to/directory/data/log/mongodb`

5. Ensure `mongod` has read and write permissions for both directories

         `sudo chmod 755 ~/path/to/directory/data/db`
         `sudo chmod 755 ~/path/to/directory/data/log/mongodb`

6. Run MongoDB

        `sudo mongod --dbpath ~/path/to/directory/data/db --logpath ~/path/to/directory/data/log/mongodb/mongo.log`


## Windows

1. Download `.msi` installer from above link by selecting **msi** from **Package** drop down
2. Run the MongoDB installer from File Explorer
3. Follow the installation wizard
-  choose the **Complete** setup to install all tools in default location, **Custom** allows you to specify where these tools are installed
-  Uncheck the box to set MongoDB up as a windows service
-  Select the box to **Install MongoDB Compass**
-  Finally select install to start installation

4. Follow [this link](https://www.mongodb.com/try/download/shell) to installl mongosh
- Select the MSI download in the platform drop down
- Double click installer file and follow prompts to download mongosh

5. Create a database directory in the command interpreter

       `cd C:\path\to\database\storage\`
       `md "\data\db"`

6. Start MongoDB by running `mongod.exe`

      `"C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe" --dbpath="C:\path\to\database\storage\data\db"`

      The MongoDB database server is running correctly if `[initandlisten] waiting for connections` is displayed
           


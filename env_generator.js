// Never run this in actual production

const crypto = require("crypto");
const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const newSecret = crypto.randomBytes(64).toString("hex");
const newJWT = crypto.randomBytes(64).toString("hex");
const secret = `SESSION_SECRET=${newSecret}`;
const JWT_secret = `JWT_SECRET=${newJWT}`;
const db_host = "DB_HOST=localhost";
const opensearch = "OPENSEARCH_URL=http://localhost:9200";

rl.question("Enter DB username: ", (db_user) => {
    rl.question("Enter DB password: ", (db_password) => {
        rl.question("Enter DB name: ", (db_name) => {
            const env_data = `${secret}\n${JWT_secret}\n${db_host}\nDB_USER=${db_user}\nDB_PASSWORD=${db_password}\nDB_NAME=${db_name}\n${opensearch}\n`;

            fs.writeFile("./.env", env_data, (err) => {
                if (err) {
                    console.error("Error generating env file: ", err);
                } else {
                    console.log("Env file successfully generated");
                }
                rl.close();
            });
        });
    });
});
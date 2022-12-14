db.createUser(
    {
        user: 'usrseriemongo',
        pwd: 'pwdseriemongo',
        roles: [
            {
                role: "readWrite",
                db: 'seriedevmongo'
            }
        ]
    }
);
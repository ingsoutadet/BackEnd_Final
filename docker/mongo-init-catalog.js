db.createUser(
    {
        user: 'usrcatalogmongo',
        pwd: 'pwdcatalogmongo',
        roles: [
            {
                role: "readWrite",
                db: 'catalogdevmongo'
            }
        ]
    }
);
const express       = require('express');
const bodyParser    = require('body-parser');
const app           = express();

app.use(bodyParser.urlencoded({extended: true}));

const port = 8000;

const items         = require('./app/routes/items');
const categories    = require('./app/routes/categories');

app.use('/items', items);
app.use('/categories', categories);

app.listen(port, () => {
    console.log('We are live on ' + port);
});



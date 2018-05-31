const express       = require('express');
const bodyParser    = require('body-parser');
const app           = express();

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(express.static('public'));

const port = 8000;

const items         = require('./app/routes/items');
const categories    = require('./app/routes/categories');
const stores        = require('./app/routes/stores');

app.use('/items', items);
app.use('/categories', categories);
app.use('/stores', stores);

app.listen(port, () => {
    console.log('We are live on ' + port);
});



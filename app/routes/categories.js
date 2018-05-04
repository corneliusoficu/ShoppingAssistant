var express     = require('express');
var router      = express.Router();

var categories_controller = require('../controllers/categories_controller');
var categoryValidator     = require('../helpers/validators_category');

router.post('/',categoryValidator, categories_controller.create_item);

module.exports = router;
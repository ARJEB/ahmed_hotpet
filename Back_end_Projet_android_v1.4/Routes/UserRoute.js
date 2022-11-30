const express = require('express')
const router = express.Router()
const UserController = require('../Controllers/UserController')
const UploadImage = require('../helpers/UploadImage')
const multer = require('multer')
const path = require('path')

upload = multer({
    storage: multer.diskStorage({
      destination: function (req, file, cb) {
        cb(null, 'uploads/');
      },
      filename: function (req, file, cb) {
        cb(
          null,
          file.fieldname + '-' + Date.now() + path.extname(file.originalname)
        );
      },
    }),
})

router.post('/register', UserController.register)

router.post('/login', UserController.login)

router.post('/refresh-token', UserController.refreshToken)

router.delete('/logout', UserController.logout)

router.get('/ShowProfile/:UserEmail',UserController.ShowProfile)

router.post('/EditProfile/:UserEmail',upload.fields([{
    name: 'ProfilePicture',
    maxCount: 1,
  },
]),UserController.EditProfile);

module.exports = router

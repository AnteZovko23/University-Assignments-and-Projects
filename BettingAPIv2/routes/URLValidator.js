const {check, validationResult} = require('express-validator')
var moment = require("moment");


const timeParamValidation = (parameter) => {

    return [check(parameter).custom(since => {
    
        var afterDateAllowed = Date.parse(moment().add(25, "days").calendar())
        var beforeDateAllowed = Date.parse(moment().subtract(7, "days").calendar())
    
        if(since > afterDateAllowed || since < beforeDateAllowed){
            return Promise.reject("Timestamp can only be 7 days before or 25 days after today.")
        } else return 1;
    
    
    }).isNumeric()]


}

const idParamValidation = (parameter) => {

    return [check(parameter).isNumeric()]


}

const errorResponse = (req, res, next) => {
  const errors = validationResult(req)
  if (errors.isEmpty()) {
    return next()
  }
  const extractedErrors = []
  errors.array().map(err => extractedErrors.push({ [err.param]: err.msg }))

  return res.status(422).json({
    errors: extractedErrors,
  })
}

module.exports = {
  timeParamValidation,
  idParamValidation,
  check,
  errorResponse,
}
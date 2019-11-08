window.onReady = function (func1) {
  if (window.addEventListener) {
    window.addEventListener('load', func1)
  } else {
    window.attachEvent('onload', func1)
  }
};

Jopper = {
  onFail(message) {
    alert(message);
  },
  setArray(vm, prop, newArr) {
    if (vm[prop] === undefined) {
      Vue.set(vm, prop, newArr);
    } else {
      oldArr = vm[prop];
      oldArr.splice(0);
      newArr.forEach(function (e) {
        oldArr.push(e)
      })
    }
  },
  handleResponse(response, dataFunction) {
    if (response.data.success) {
      dataFunction(response.data.data);
    } else {
      Jopper.onFail(response.data.message);
    }
  }
};
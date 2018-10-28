window.onReady = function (func1) {
    if (window.addEventListener) {
        window.addEventListener('load', func1)
    } else {
        window.attachEvent('onload', func1)
    }
};

Jopper = {
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
    }
};
// jquery
$(document).ready(function () {
  // 在庫一覧出庫ボタン押下時
  $(".button.new-entry").click(function () {
    // モーダル表示
    $(".ui.modal.shipping-modal").modal("show");
  });

  // 更新ボタン押下時
  $(".inverted.button").click(function () {
    // モーダル表示
    $(".ui.modal.update-modal").modal("show");
  });

  $("#date_calendar").calendar({
    type: "date",
    formatter: {
      date: function (date) {
        var day = ("0" + date.getDate()).slice(-2);
        var month = ("0" + (date.getMonth() + 1)).slice(-2);
        var year = date.getFullYear();
        return year + "/" + month + "/" + day;
      },
    },
  });
});
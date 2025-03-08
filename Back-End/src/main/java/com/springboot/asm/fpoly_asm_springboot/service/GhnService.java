package com.springboot.asm.fpoly_asm_springboot.service;

import com.springboot.asm.fpoly_asm_springboot.dto.request.ghn.DistrictRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.request.ghn.ShippingMethodRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.request.ghn.ShippingOrderRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.request.ghn.WardRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.response.ghn.*;

import java.util.List;

public interface GhnService {

    ShippingOrderResponse createShippingOrder(ShippingOrderRequest request);
    List<ProvinceResponse>  getProvinces();
    List<WardResponse> getWards(WardRequest request);
    List<DistrictResponse> getDistricts(DistrictRequest districtRequest);
    List<ShippingMethodResponse> createShippingMethod(ShippingMethodRequest request);
}

/*
 * Copyright 2019 readonlynetwork.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.readonlynetwork.example.cdi_custom_collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test available beds
 *
 * @author readonlynetwork.org
 */
@RunWith(Arquillian.class)
public class ForUseTest {
	
	@Deployment
    public static Archive<?> createTestArchive() {
		//Setup settings
        return ShrinkWrap.create(WebArchive.class, "testbeds.war")
        		.addPackages(true, "org.readonlynetwork.example.cdi_custom_collection")//all package
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");//needed for CDI   
	}
	
	
	@Inject
	@ForUse
	private Instance<Bed> usedBed;
	
	@Inject
	@Any
	private Instance<Bed> allBeds;
	
	@Test
	public void testUsedBed() {
		List<Bed> forUse = usedBed.stream().collect(Collectors.toList());
		
		assertEquals(2, forUse.size());
		
		assertFalse("Should not be Gold Bed!", forUse.get(0) instanceof GoldBed);
		assertFalse("Should not be Gold Bed!", forUse.get(1) instanceof GoldBed);
	}
	
	@Test
	public void testAllBed() {
		List<Bed> goldBed = allBeds.stream().filter(x -> x instanceof GoldBed).collect(Collectors.toList());
		List<Bed> airBed = allBeds.stream().filter(x -> x instanceof AirBed).collect(Collectors.toList());
		List<Bed> classicBed = allBeds.stream().filter(x -> x instanceof ClassicBed).collect(Collectors.toList());
		
		assertEquals("Should be a Gold Bed!", 1, goldBed.size());
		assertEquals("Should be a Air Bed!", 1, airBed.size());
		assertEquals("Should be a Classic Bed!", 1, classicBed.size());
	}
}
